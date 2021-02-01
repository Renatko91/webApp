<template>
    <div>
        <worker-form :workers = "workers" :workerAttr = "worker" />
        <worker-row v-for = "worker in workers"
            :key = "worker.id"
            :workers = "workers"
            :worker = "worker"
            :editMethod = "editMethod" />
    </div>
</template>

<script>
    import MessageForm from 'components/WorkerForm.vue'
    import MessageRow from 'components/WorkerRow.vue'

    export default {
        props: ['workers'],
        components: {
            WorkerForm,
            WorkerRow
        },
        data() {
            return {
                worker: null
            }
        },
        methods: {
            editMethod: function(worker) {
                this.worker = worker;
            }
        }
    }
</script>