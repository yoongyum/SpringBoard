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

function deleteComment(commentSeq){
    const params = {
        commentSeq: commentSeq /*삭제할 댓글 번호*/ 
    }
    $.ajax({
        type:"DELETE",
        url:"/comment/delete",
        data: params
    }).done(function (fragment){
        alert('댓글이 삭제되었습니다.✋')
        $('#commentContainer').replaceWith(fragment);
    })
}