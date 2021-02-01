<template>
    <div>
        <worker-list :workers = "workers" />
    </div>
</template>

<script>
    import WorkerList from 'components/WorkerList.vue'

    export default {
        components: {
            WorkerList
        },
        data() {
            return {
                workers: frontendData.workers
            }
        },
    }
</script>