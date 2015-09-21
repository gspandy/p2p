/**
 * Created by Administrator on 2015/9/14.
 */

//检测是否具备了滚动加载数据块的条件
function CheckScrollSlide(id,className) {
    var oParent =document.getElementById(id);
    var oRoom = getByClass(oParent,className);
    var boxH = oRoom[oRoom.length-1].offsetHeight;
    var lastBoxH = oRoom[oRoom.length-1].offsetTop;
    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    var height = document.documentElement.clientHeight;
    return(lastBoxH<scrollTop+height)?true:false;
}

//根据class获取元素
function getByClass(parent,clsName){
    var boxArr = new Array();  // 用来存储所有获取到的class为box的元素
    var oElements = parent.getElementsByTagName('*');
    for(var i=0;i<oElements.length;i++){
        if(oElements[i].className==clsName) {
            boxArr.push(oElements[i]);
        }

    }
    console.log('boxArr'+boxArr.length);
    return boxArr;

}