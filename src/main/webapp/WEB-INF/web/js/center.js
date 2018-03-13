/**
 * Created by piaoxuehua on 2018/3/4.
 */
function searchon() {
    document.getElementById("search-circle").style.width="250px";
    document.getElementById("search-input").style.display = "block";
    document.getElementById("search-input").focus();
}
function searchout() {
    document.getElementById("search-circle").style.width="74px";
    document.getElementById("search-input").style.display = "none"
}
function savaUser(userId) {
    var form = document.getElementsByTagName("form")[0];
    var url = 'http://localhost:8080/user/';
    url += userId;
    url += '/updateUserinfo';
    form.action =  url;
}
window.onload=function () {
    //验证原密码是否正确
    $("#userpass-old").focus(function() {
        $("#userpass-old").blur(function() {
            $.ajax({
                url:urlPre+"/user/checkOldPass",
                type:"POST",
                contentType: "application/json",
                dataType : 'json',
                data:JSON.stringify({
                    "oldPass":$("#userpass-old").val()
                }),
                success:function (data) {
                    if(data.code===200){
                        $(".erro-icon")[0].style.display = "none";
                        $(".right-icon")[0].style.display = "block";
                    }else {
                        $(".erro-icon")[0].style.display = "block";
                        $(".right-icon")[0].style.display = "none";
                    }
                },
                error:function () {
                    alert("网络故障，请稍后再试");
                }
            });
        })
    });


    $("#userpasssure").focus(function() {
        $("#userpasssure").blur(function() {
            if ($("#userpassnew").val()!==$("#userpasssure").val()){
                $(".erro-icon")[1].style.display = "block";
                $(".right-icon")[1].style.display = "none";
            }else {
                $(".erro-icon")[1].style.display = "none";
                $(".right-icon")[1].style.display = "block";

            }
        })
    });
};
function updatePass() {
    var a = $(".right-icon")[0].style.display === "block";
    var b = $(".right-icon")[1].style.display === "block";
    if(a&&b){
        $.ajax({
            url:urlPre+"/user/updatePassword",
            type:"POST",
            contentType: "application/json",
            dataType : 'json',
            data:JSON.stringify({
                "oldPass":$("#userpass-old").val(),
                "password":$("#userpasssure").val()
            }),
            success:function (data) {
                if(data.code===200){
                    alert("修改成功");
                    $("#userpass-old").val("");
                    $("#userpassnew").val("");
                    $("#userpasssure").val("");
                    $(".right-icon")[0].style.display = "none";
                    $(".right-icon")[1].style.display = "none";
                }else {
                    alert("修改失败")
                }
            },
            error:function () {
                alert("网络故障，请稍后再试");
            }
        });
    }
    else {
        alert("xczxc");
    }
}
var image = '';
function selectImage(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        document.getElementById('body-userpic').src = evt.target.result;
        image = evt.target.result;
    }
    reader.readAsDataURL(file.files[0]);
}
function uploadImage() {
    var formData = new FormData();
    var img_file = document.getElementById("file");
    var fileObj = img_file.files[0];
    formData.append("file",fileObj);
    $.ajax({
        type: 'POST',
        url: urlPre+'/user/uploadPhoto',
        data:formData,
        processData:false,
        contentType: false,
        dataType:"json",
        success: function (data) {
            if (data.code === 200) {
                location.reload(true);
                alert('上传成功');
            } else {
                alert('上传失败');
            }
        },
        error: function (err) {
            alert('网络故障');
        }
    });
}