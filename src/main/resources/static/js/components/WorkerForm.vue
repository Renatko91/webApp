<template>
    <div>
        <input type = "text" placeholder = "Write..." v-model = "text" />
        <input type = "button" value = "Save"  @click = "save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }

        return -1;
    }

    export default {
        props: ['workers','workerAttr'],
        data: function() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            workerAttr: function(newVal, oldWal) {
                this.text = newVal.name;
                this.id = newVal.id;
            }
        },
        methods: {
            save: function() {
                var worker = { name: this.text };

                if (this.id) {
                    workerApi.update({id: this.id}, worker).then(result =>
                    result.json().then(data => {
                    var index = getIndex(this.workers, data.id);
                    this.workers.splice(index, 1, data);
                    this.text = '';
                    this.id = null;
                }))
                } else {
                    workerApi.save({}, worker).then(result =>
                        result.json().then(data => {
                            this.workers.push(data);
                            this.text = '';
                        }))
                }
            }
        }
    }
</script>