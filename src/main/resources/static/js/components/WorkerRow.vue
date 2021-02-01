<template>
    <div>
        <i>{{ worker.id }}</i> {{ worker.name }}
        <input type = "button" value = "Edit"  @click = "update" />
        <input type = "button" value = "X" @click = "del" />
    </div>
</template>

<script>
    props: ['worker', 'editMethod', 'workers'],
    methods: {
        update: function() {
            this.editMethod(this.worker);
        },
        del: function() {
            workerApi.remove({id: this.worker.id}).then(result => {
                if (result.ok) {
                    this.workers.splice(this.workers.indexOf(this.worker), 1);
                }
            })
        }
    }
</script>