console.log('boardComment.js Join Success');

const cmtWriter = document.getElementById('cmtWriter');
const cmtText = document.getElementById('cmtText');
const cmtPostBtn = document.getElementById('cmtPostBtn');
const cmtListArea = document.getElementById('cmtListArea');

document.getElementById('cmtPostBtn').addEventListener('click', ()=>{
   if(cmtText.value == null || cmtText.value == ''){
      alert('댓글을 입력해 주세요.');
      return;
   }else{
      let CommentData = {
         bno:bnoVal,
         writer:cmtWriter.innerText,
         content:cmtText.value
      };
      console.log('▼ CommentData ▼');
      console.log(CommentData);
   };

   postCommentToServer(CommentData).then(result =>{
      if(result == '1'){
         alert('댓글 등록 성공');
         cmtText.value = '';
         spreadCommentList(bnoVal);
      };
   });
});

async function postCommentToServer(CommentData){
   try {
      const url = '/comment/register';
      const config = {
         method:'put',
         header:{
            'content-type':'application/json; charset=UTF-8'
         },
         body:JSON.stringify(CommentData)
      };

      const resp = await fetch(url,config);
      const result = await resp.text();
      
      return result;
   } catch (error) {
      console.log(error);
   }
}