<template>
    <div>
        <worker-form :workers = "workers" :workerAttr = "worker" />
        <worker-row v-for = "worker in workers"
            :key = "worker.id"
            :workers = "workers"
            :worker = "worker"
            :editMethod = "editMethod"
            :deleteMethod = "deleteMethod"/>
    </div>
</template>

<script>
    import WorkerRow from 'components/WorkerRow.vue'
    import WorkerForm from 'components/WorkerForm.vue'

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
            editMethod(worker) {
                this.worker = worker
            },
            deleteMethod(worker) {
                this.$resource('/worker{/id}').remove({id: worker.id}).then(result => {
                     if(result.ok) {
                         this.workers.splice(this.workers.indexOf(worker), 1)
                     }
                })
            }
        }
    }
</script>