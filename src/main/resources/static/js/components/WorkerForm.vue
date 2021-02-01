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
                return i
            }
        }

        return -1
    }

    export default {
        props: ['workers','workerAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            workerAttr(newVal, oldWal) {
                this.text = newVal.name
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                const worker = { name: this.text }

                if (this.id) {
                    this.$resource('/worker{/id}').update({id: this.id}, worker).then(result =>
                    result.json().then(data => {
                        const index = getIndex(this.workers, data.id)
                        this.workers.splice(index, 1, data)
                        this.text = ''
                        this.id = null
                    })
                )
                } else {
                    this.$resource('/worker{/id}').save({}, worker).then(result =>
                        result.json().then(data => {
                            this.workers.push(data)
                            this.text = ''
                        })
                    )
                }
            }
        }
    }
</script>