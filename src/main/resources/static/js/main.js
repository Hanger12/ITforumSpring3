new Vue({
    el:'#app',
    data: {
        title: "hello world",
        entrance: true,
        isSearch: false
    }
});
new Vue({
    el:'#Search',
    data:{
        isSearch:false
    }
});

new Vue({
    el: '#contents',
    data: {
        players: [
            {
                id: '1',
                name: 'Lionel Messi',
                description: 'Argentina s superstar'
            },
            {
                id: '2',
                name: 'Christiano Ronaldo',
                description: 'World #1-ranked player from Portugal'
            }
        ]
    }
});