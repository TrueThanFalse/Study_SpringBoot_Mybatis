console.log('boardComment.js Join Success');
console.log('bno : ', bnoVal);

const cmtWriter = document.getElementById('cmtWriter');
const cmtText = document.getElementById('cmtText');
const cmtPostBtn = document.getElementById('cmtPostBtn');
const cmtListArea = document.getElementById('cmtListArea');

document.getElementById('cmtPostBtn').addEventListener('click', ()=>{
   if(cmtText.value == null || cmtText.value == ''){
      alert('댓글을 입력해 주세요.');
      return false;
   }else{
      let CommentData = {
         bno : bnoVal,
         writer : cmtWriter.innerText,
         content : cmtText.value
      };
      console.log('▼ CommentData ▼');
      console.log(CommentData);

      postCommentToServer(CommentData).then(result =>{
         if(result === "1"){
            alert('댓글 등록 성공');
            cmtText.value = '';
            spreadCommentList(bnoVal);
         };
      });
   };
});

async function postCommentToServer(CommentData){
   try {
      const url = '/comment/register';
      const config = {
         method : 'put',
         // 반드시 소문자 headers, 대문자 H는 config에서 인식 못함
         headers : {
            'content-type':'application/json; charset=utf-8'
         },
         body : JSON.stringify(CommentData)
      };

      const resp = await fetch(url,config);
      const result = await resp.text();
      
      return result;
   } catch (error) {
      console.log(error);
   };
};

async function getCommentListFromServer(bno, page){
   try {
      const resp = await fetch('/comment/getList/' + bno + '/' + page);
      const result = await resp.json();
      
      return result;
   } catch (error) {
      console.log(error);
   };
};

async function spreadCommentList(bno, page=1){
   // 들어오는 page가 없으면 1, 있으면 그 page의 값으로

   getCommentListFromServer(bno, page).then(result =>{
      console.log(result); // ph

      if(result.cmtList.length > 0){
         
         if(page == 1){ // page가 1일 때만
            // ul에 원래 있던 html 값 초기화
            cmtListArea.innerHTML = '';
         }

         for(let cvo of result.cmtList){
            let li = `<li data-cno="${cvo.cno}" class="list-group-item">`;
            li += `<div class="ms-2 me-auto">`;
            li += `<div class="fw-bold">${cvo.writer}</div>`;
            li += `${cvo.content}`;
            li += `</div>`;
            li += `<span>${cvo.modAt}</span>`;
            li += `<button type="button" class="btn btn-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
            li += `<button type="button" class="btn btn-danger del">삭제</button>`;
            li += `</li>`;
            cmtListArea.innerHTML += li;

            // page 처리
            let moreBtn = document.getElementById('moreBtn');

            // 현재 페이지 번호가 전체 페이지 번호보다 작다면...
            // => 아직 더 볼 수 있는 페이지가 존재 한다면...
            if(result.pgvo.pageNo < result.endPage){
               // 숨김 속성 해제
               moreBtn.style.visibility = 'visible'; // 표시됨
               // 페이지 값 +1
               moreBtn.dataset.page = page + 1;
            }else{
               moreBtn.style.visibility = 'hidden'; // 숨김
            }
         }
      }else{
         let li = `<li class="list-group-item">Comment List Empty</li>`;
         cmtListArea.innerHTML = li;
      }
   });
};

// 클릭 이벤트
document.addEventListener('click', (e)=>{
   if(e.target.classList.contains('mod')){ // 댓글 수정 버튼
      // target을 포함하고 있는 li 태그 가져오기
      let li = e.target.closest('li');

      // nextSibling : 같은 부모의 다음 형제 객체를 반환
      // 값이 단순한 문자열이라도 객체로 반환되므로 주의해야 함
      let cmtText = li.querySelector('.fw-bold').nextSibling;

      // nodeValue : 현재 선택한 노드의 value를 반환
      document.getElementById('cmtTextModal').value = cmtText.nodeValue;

      document.getElementById('cmtModalBtn').setAttribute('data-cno', li.dataset.cno);
   }else if(e.target.id == 'cmtModalBtn'){ // 모달창 수정 버튼
      let cmtDataModal = {
         cno : e.target.dataset.cno,
         content : document.getElementById('cmtTextModal').value
      };

      editCommentToServer(cmtDataModal).then(result =>{
         if(result === "1"){
            // 모달창 닫기
            document.querySelector('.btn-close').click();

            alert("수정 성공");
            
            spreadCommentList(bnoVal);
         };
      });

   }else if(e.target.classList.contains('del')){ // 댓글 삭제 버튼
      

   }else if(e.target.id == 'moreBtn'){ // 댓글 더보기(more) 버튼
      spreadCommentList(bnoVal, parseInt(e.target.dataset.page));
   }
});

async function editCommentToServer(cmtDataModal){
   try {
      const url = '/comment/edit';
      const config = {
         method : 'put',
         headers : {
            'content-type':'application/json; charset=utf-8'
         },
         body : JSON.stringify(cmtDataModal)
      };

      const resp = await fetch(url, config);
      const result = await resp.text();

      return result;
   } catch (error) {
      console.log(error);
   }
}