/**
 * Created by piaoxuehua on 2018/3/4.
 */

window.onload = function () {
    //初始化表情
    $("#write-top").emoji({
        button: "#btn",
        showTab: false,
        animation: 'slide',
        icons: [{
            name: "QQ表情",
            path: urlPre + "/face/img/qq/",
            maxNum: 91,
            excludeNums: [41, 45, 54],
            file: ".gif"
        }]
    });

    //发表评论
    var btn = document.getElementById("btn-huifu");
    btn.onclick = function () {
        var s = document.getElementById("write-top");
        $.ajax({
            url: urlPre + "/addForumRemark",
            type: "POST",
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify({
                "content": s.innerHTML,
                "forumId": $('#id').val()
            }),
            success: function (data) {
                if (data.code === 200) {
                    alert("发表成功");
                    window.location.href = urlPre + "/forum/" + $('#id').val();
                } else {
                    alert(data.msg);
                }
            },
            error: function () {
                alert("发表失败")
            }
        });
    };
};


function searchon() {
    document.getElementById("search-circle").style.width = "250px";
    document.getElementById("search-input").style.display = "block";
    document.getElementById("search-input").focus();
}
function searchout() {
    document.getElementById("search-circle").style.width = "74px";
    document.getElementById("search-input").style.display = "none"
}
function favor(forumId) {
    // var json = {
    //     "forumId": forumId
    // };
    $.ajax({
        url: urlPre + "/forum/favor",
        type: "POST",
        dataType: 'json',
        data: {
            "forumId":forumId
        },
        success: function (data) {
            if (data.code === 200) {
                alert("收藏成功");
                // $('#likeIcon').src = 'localhost:8080/image/articlepage-likeicon_selected.png';
            } else {
                alert("您已经收藏过");
            }
        },
        error: function () {
            alert("网络故障，请稍后再试");
        }
    });
}
