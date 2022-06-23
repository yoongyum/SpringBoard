//댓글 (수정) 버튼 클릭 시
function edit(seq)  {
    document.getElementById("comment-view"+seq).style.display = 'none';
    document.getElementById("comment-edit"+seq).style.display = 'block';
}
//댓글 -> 수정 (취소) 버튼 클릭 시
function cancel(seq)  {
    const view = document.getElementById("comment-view"+seq);
    const edit = document.getElementById("comment-edit"+seq);
    view.style.display = 'block';
    edit.style.display = 'none';
}