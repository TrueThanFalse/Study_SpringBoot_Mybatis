console.log('boardDetail.js Join Success');

document.getElementById('listBtn').addEventListener('click', ()=>{
   // 경로 이동 방법 2가지
   location.href = "/board/list"; // 가장 일반적인 방법
   // location.replace("/board/list");

   // 왜 이렇게 하는가?
   // a태그를 사용하여 버튼을 감싸서 경로 이동을 할 경우
   // 버튼의 css가 이상해질 수 있다. 그것을 방지할 수 있다.

   // 이런 방식으로 Form 태그의 속성을 수정하고 경로 이동을 할 수 있다.
   // const delForm = document.getElementById('delForm');
   // delForm.setAttribute('action', '/board/list');
   // delForm.setAttribute('method', 'get');
   // delForm.bno.remove();
   // delForm.submit();
});

document.getElementById('delBtn').addEventListener('click', ()=>{
   document.getElementById('delForm').onsubmit();
});

document.getElementById('modBtn').addEventListener('click', ()=>{
   // modify.html을 만들지 않고 detail.html을 수정하여
   // modify.html처럼 만드는 방법
   document.getElementById('title').readOnly = false;
   document.getElementById('content').readOnly = false;
   
   document.getElementById('modBtn').remove();
   document.getElementById('delBtn').remove();

   // 새로운 버튼 생성
   let editBtn = document.createElement('button');
   editBtn.classList.add('btn', 'btn-success');
   editBtn.setAttribute('type', 'submit');
   editBtn.innerText = "Edit";

   // 새로 생성한 버튼(editBtn)을 id 위치에 넣어주기
   document.getElementById('DivBtn').appendChild(editBtn);
})