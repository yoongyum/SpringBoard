//댓글 입력
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

//댓글 삭제 method
function deleteComment(commentSeq){
    const params = {
        commentSeq: commentSeq //삭제할 댓글 번호
    }
    $.ajax({
        type: "DELETE",
        url:"/comment/delete",
        data: params
    }).done(function (fragment){
        $('#commentContainer').replaceWith(fragment);
    })
}

//댓글 수정 method
function updateComment(commentSeq){
    const content = $('#comment-content'+commentSeq);
    if ( content.val() === ""){
        content.focus();
        $('#error'+commentSeq).css({display:'block'});
        return;
    }
    $('#error'+commentSeq).css({display:'none'});
    const params = {
        commentSeq: commentSeq, //수정할 댓글 번호
        boardSeq: $('#boardSeq').val(),
        commentContent: content.val()
    }
    $.ajax({
        type: "PUT",
        url: "/comment/update",
        data: params
    }).done(function (fragment){
        alert(fragment)
        $('#commentContainer').replaceWith(fragment);
    })
}

//대댓글 입력
function insertReply(parentSeq){
    const content = $('#reply-content'+parentSeq);
    if( content.val() === ""){
        content.focus();
        $('#reply-error'+parentSeq).css({display:'block'});
        return;
    }
    $('#reply-error'+parentSeq).css({display:'none'});
    const params = {
        parentSeq: parentSeq, //부모 댓글
        boardSeq: $('#boardSeq').val(),
        commentContent: content.val()
    }
    $.ajax({
        type: "POST",
        url: "/comment/reply",
        data: params
    }).done(function (fragment){
        $('#replyContainer'+parentSeq).replaceWith(fragment);
    })
}