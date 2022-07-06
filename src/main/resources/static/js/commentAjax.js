$("#ajax_button").click(()=>{
    const content = $('#commentContent');
    if ( content.val() === ""){
        content.focus();
        $('#error').css({display:'block'});
        return;
    }
    $('#error').css({display:'none'});
    const params = {
        boardSeq: $('#boardSeq').val(),
        commentContent: content.val()
    };
    //ajax 통신
    $.ajax({
        type:"POST",
        url:"/comment/insert",
        data: params,
    }).done(function(fragment){
        $('#commentContainer').replaceWith(fragment);
    })
})