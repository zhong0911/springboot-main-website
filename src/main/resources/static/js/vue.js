const App = {
    data() {
        return {
            cornetTime: getCurrentTime()
        }
    }, methods: {
        inputQuery() {
            inputQuery();
        }
    },
    mounted() {
        setInterval(() => {
            this.cornetTime = getCurrentTime();
        }, 1000);
    }
}

Vue.createApp(App).mount('#app')


function getCurrentTime() {
    function addLeadingZero(number) {
        return number < 10 ? "0" + number : number;
    }

    const now = new Date();
    const hours = addLeadingZero(now.getHours());
    const minutes = addLeadingZero(now.getMinutes());
    const seconds = addLeadingZero(now.getSeconds());
    return `${hours}:${minutes}:${seconds}`;
}


function inputQuery() {
    let query = $("#query").val();
    if (query === "") {
        showData({s: []});
        return;
    }
    let data = {'wd': query, 'p': '3', 'cb': 'showData', 't': '1324113456725'};
    $.ajax({
        async: false,
        url: "http://suggestion.baidu.com/su",
        type: "GET",
        dataType: 'jsonp',
        jsonp: 'jsoncallback',
        data: data,
        timeout: 5000,
        success: function (json) {
        }
    });
}


function showData(data) {
    $("#keyword-list").empty();
    for (let i in data['s']) {
        if (i > 5) break;
        let keyword = data['s'][i];
        $("#keyword-list").append(`<a href="/search?query=${keyword}" class="small list-group-item list-group-item-action">${keyword}</a>`);
    }
}