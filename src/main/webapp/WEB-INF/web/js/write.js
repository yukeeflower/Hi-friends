/**
 * Created by piaoxuehua on 2018/3/8.
 */
var pics ='';
function shows(a) {
    $('.buttonText').text(a);
}
function subimg() {
    document.getElementById("image-box").style.display="block"
}
function addForum() {
    var formData = new FormData();
    formData.append("title",$("#article").val());
    formData.append("content",$("#text").val());
    formData.append("tags",$("#buttonText").val());
    formData.append("pics",pics);
    $.ajax({
        type: 'POST',
        url: urlPre+'/forum/addForum',
        data:formData,
        processData:false,
        contentType: false,
        dataType:"json",
        success:function (data) {
            if (data.code===200){
                window.location.href = urlPre+"/user/toMyHomePage";
            }else {
                alert("发表失败");
            }
        },
        error:function (err) {
            alert("网络故障，请稍后再试");
        }
    });
}

var image = '';

var index = 0;

function selectImage(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function(evt) {
        document.getElementById("image-box").innerHTML += "<img src='' class='img'>";
        document.getElementsByClassName('img')[index].src = evt.target.result;
        index++;
        image = evt.target.result;
    };
    reader.readAsDataURL(file.files[0]);
    upload(file.files[0]);
}
function upload(file) {
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        type: 'POST',
        url: urlPre+'/forum/uploadForumPics',
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        success: function(data) {
            if (data.code === 200) {
                pics += data.msg;
                pics += ";";
            } else {
                alert('上传失败');
            }
        },
        error: function(err) {
            alert('网络故障');
        }
    });
};

