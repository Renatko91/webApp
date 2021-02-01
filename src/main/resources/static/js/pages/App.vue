<template>
    <div>
        <workers-list :workers = "workers" />
    </div>
</template>

<script>
    import WorkersList from 'components/WorkerList.vue'

    export default {
        components: {
            WorkersList
        },
        data() {
            return {
                workers: frontendData.workers
            }
        }
    }
</script>