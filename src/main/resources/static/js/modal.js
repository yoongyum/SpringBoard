function disappearModal(){
    document.getElementById("viewModalContainer").style.display = 'none';
}
function initMouseClickEvent(){
    $(document).mouseup(function(e){
        var sch_container = $("modal-view");
        if(sch_container.has(e.target).length ===0)
            sch_container.hide();

    });

}
