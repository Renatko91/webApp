<template>
    <div>
        <i>{{ worker.id }}</i> {{ worker.name }}
        <input type = "button" value = "Edit"  @click = "update" />
        <input type = "button" value = "X" @click = "del" />
    </div>
</template>

<script>
    export default {
        props: ['worker', 'editMethod', 'deleteMethod', 'workers'],
        methods: {
            update() {
                this.editMethod(this.worker)
            },
            del() {
                this.deleteMethod(this.worker)
            }
        }
    }
</script>