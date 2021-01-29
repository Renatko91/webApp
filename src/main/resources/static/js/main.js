function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var workerApi = Vue.resource('/worker{/id}');

Vue.component('worker-form', {
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
    template:
        '<div>' +
        '<input type = "text" placeholder = "Write..." v-model = "text" />' +
        '<input type = "button" value = "Save"  @click = "save" />' +
        '</div>',
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
                    })
                )
            } else {
                workerApi.save({}, worker).then(result =>
                     result.json().then(data => {
                        this.workers.push(data);
                        this.text = '';
                     })
                )
            }
        }
    }
});

Vue.component('worker-row', {
    props: ['worker', 'editMethod', 'workers'],
    template:
        '<div>' +
        '<i>{{ worker.id }}</i> {{ worker.name }}' +
        '<input type = "button" value = "Edit"  @click = "update" />' +
        '<input type = "button" value = "X" @click = "del" />' +
        '</div>',
    methods: {
            update: function() {
                this.editMethod(this.worker);
            },
            del: function() {
                workerApi.remove({id: this.worker.id}).then(result => {
                        if (result.ok) {
                            this.workers.splice(this.workers.indexOf(this.worker), 1);
                        }
                    }
                )
            }
    }
});

Vue.component('workers-list', {
    props: ['workers'],
    data: function() {
        return {
            worker: null
        }
    },
    template:
        '<div>' +
        '<worker-form :workers = "workers" :workerAttr = "worker" />' +
        '<worker-row v-for = "worker in workers" ' +
        ':workers = "workers" :worker = "worker" :key = "worker.id" :editMethod = "editMethod" />' +
        '</div>',
    created: function() {
        workerApi.get().then(result =>
        result.json().then(data =>
        data.forEach(worker => this.workers.push(worker))))
    },
    methods: {
        editMethod: function(worker) {
            this.worker = worker;
        }
    }
});

var app = new Vue({
  el: '#app',
  template:
    '<workers-list :workers = "workers" />',
  data: {
    workers: []
  }
});