/**
 * Created by piaoxuehua on 2018/3/1.
 */
//顶部菜单栏切换动效
window.onload =function () {
    // searchon()

}
// function searchon() {
//     var a=document.getElementById("search-circle").offsetWidth;
//     var b=document.getElementById("search-input");
//     // var c=parseInt(document.getElementById("search-circle").style.width);
//
//     alert(a)
//     if(a==74){
//         // b.style.display="none"
//         b.style.display="block"
//         alert("if")
//     }
//     else {
//         alert("else")
//         b.style.display="block"
//     }
// }
function searchon() {
    document.getElementById("search-circle").style.width="250px";
    document.getElementById("search-input").style.display = "block";
    document.getElementById("search-input").focus();
}
function searchout() {
    document.getElementById("search-circle").style.width="74px";
        document.getElementById("search-input").style.display = "none"
}
function golog() {
    window.location.href = urlPre + "/home/toLoginPage";
    // $.ajax({
    //     url : 'http://localhost:8080/toLoginPage',
    //     cache : false,
    //     async : false,
    //     type : "GET",
    //     dataType : 'json/xml/html',
    //     success : function (result){
    //         alert("dasd")
    //     }
    // });
}
function goMySelf(userId) {
    window.location.href =  urlPre + "/user/"+userId;
}