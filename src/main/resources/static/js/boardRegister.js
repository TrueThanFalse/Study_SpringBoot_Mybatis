console.log('boardRegister.js Join Success');

document.getElementById('trigger').addEventListener('click',()=>{
   document.getElementById('files').click();
});

const regExp = new RegExp("\.(exe|sh|bat|js|dll|msi)$"); // 실행 파일들
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
   if(regExp.test(fileName)){
      return 0;
   }else if(fileSize > maxSize){
      return 0;
   }else{
      return 1;
   }; 
};

document.addEventListener('change', (e)=>{
   if(e.target.id == 'files'){
      // multiple : 배열로 들어옴
      const fileObject = document.getElementById('files').files;
      console.log(fileObject);

      const div = document.getElementById('fileZone');

      // 이전의 파일 업로드를 했던 파일들이 있다면 초기화(제거)
      div.innerHTML = "";
      // 한번 true가 되버렸다면 그것을 false로 복구
      document.getElementById('regBtn').disabled = false;

      let isOK = 1; // isOK : 모든 파일의 검증 결과
      let ul = `<ul class="list-group list-group-flush">`;
         for(let file of fileObject){
            // validResult : 개별 파일의 검증 결과
            let validResult = fileValidation(file.name, file.size);
            isOK *= validResult; // 하나씩 모든 파일에 대한 확인

            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<div class="text-success-emphasis">업로드 가능</div>' : '<div class="text-danger-emphasis">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${validResult ? 'success' : 'danger'}">${file.size}Byte</span>`;
            ul += `</li>`;
         };
      ul += `</ul>`;
      div.innerHTML = ul;

      if(isOK == 0){
         // 파일 중 validation을 통과하지 못한 것이 있다면...
         document.getElementById('regBtn').disabled = true;
      };
   };
});