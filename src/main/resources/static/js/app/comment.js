// 데이터 전송 객체 생성!
var comment = {
  // 이벤트 등록
  init: function() {
    var _this = this;
    // 생성 버튼 클릭 시!
    const createBtn = document.querySelector('#comment-create-btn');
    // 이벤트 감시 시, 수행할 메소드 연결!
    createBtn.addEventListener('click', function() {
      _this.create();
    });

    // update btn
    const updateBtns = document.querySelectorAll('.comment-update-btn');
    // update btn event 등록
    updateBtns.forEach(function(item) {
      item.addEventListener('click', function() { // 클릭 이벤트 발생시,
        var form = this.closest('form'); // 클릭 이벤트가 발생한 버튼에 제일 가까운 폼을 찾고,
        _this.update(form); // 해당 폼으로, 업데이트 수행한다!
      });
    });

    // delete btn
    const deleteBtn = document.querySelectorAll('.comment-delete-btn');
    // delete btn event 등록
    deleteBtn.forEach(function(item) {
      item.addEventListener('click', function() { // 클릭 이벤트 발생시,
        var commentId = this.getAttribute('value'); // 클릭 이벤트가 발생한 버튼에 제일 가까운 폼을 찾고,
        _this.delete(commentId); // 해당 폼으로, 업데이트 수행한다!
      });
    });
  },
  // 댓글 등록
  create: function() {
    // 데이터
    var data = {
      author: document.querySelector('#comment-author').value,
      content: document.querySelector('#comment-content').value,
    };

    var split = location.pathname.split('/');
    var articleId = split[split.length - 1];

    fetch('/test/api/comments/' + articleId, { // 요청을 보냄
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(function(response) { // 응답 처리
      if (response.ok) { // 성공
        alert('댓글이 등록되었습니다.');
        window.location.reload();
      } else { // 실패
        alert('댓글 등록 실패..!');
      }
    });
  },
  update: function(form) {
    var data = {
      id: form.querySelector('#comment-id').value,
      author: form.querySelector('#comment-author').value,
      content: form.querySelector('#comment-content').value,
    };

    var split = location.pathname.split('/');
    var articleId = split[split.length - 1];

    fetch('/test/api/comments/' + data.id, {
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(function(response) {
      if (response.ok) {
        alert('댓글이 수정되었습니다.');
      } else {
        alert('댓글 수정에 실패했습니다.');
      }
      window.location.reload(true); // page reload
    });
  },
  delete: function(commentId) {
    // 요청을 보냄
    fetch('/test/api/comments/' + commentId, {
      method: 'DELETE',
    }).then(function(response) { // 응답 처리
      if (response.ok) { // 성공
        alert('댓글이 삭제 되었습니다.');
        // DB에서 사라졌으나, 화면에는 남아있음! 이를 위해, CSS로 화면에서 감춤!
        document.querySelector(`#comments-${commentId}`).style.display = 'none';
      } else { // 실패
        alert(JSON.stringify(response));
      }
    });
  },
};

// 객체 초기화!
comment.init();