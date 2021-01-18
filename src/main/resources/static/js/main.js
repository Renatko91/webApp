Vue.component('worker-row', {
    props: ['worker'],
    template: '<div> <i>{{ worker.id }}</i> {{ worker.text }} </div>'
});

Vue.component('workers-list', {
    props: ['workers'],
    template:
    '<div>' +
    '<worker-row v-for = "worker in workers" :worker = "worker" :key = "worker.id" />' +
    '</div>'
});

var app = new Vue({
  el: '#app',
  template: '<workers-list :workers = "workers" />',
  data: {
    workers: [
        { id: '1', text: 'Andrey' },
        { id: '2', text: 'Rene' },
        { id: '3', text: 'Sergey' },
    ]
  }
});