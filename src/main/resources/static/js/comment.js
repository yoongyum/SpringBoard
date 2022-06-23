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

//댓글 입력창 동적크기 변환
function resize(obj){
    // const hei = window.getComputedStyle(obj).height
    if (obj.scrollHeight >= 90){
        obj.style.height = "0px";
        obj.style.height = (16 + obj.scrollHeight) + "px";
    }
}

//댓글 유효성 검사
function checkForm(frm,contentId, errorId) {
    const form = document.forms.namedItem(frm);
    const content = document.getElementById(contentId);
    if (content.value === "") {
        document.getElementById(errorId).style.display = "block";
    } else {
        form.submit();
    }
    return false;
}