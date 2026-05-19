const modifyBtn = document.getElementById('modify-btn');

if (modifyBtn) {
    const params = new URLSearchParams(location.search);
    const id = params.get('id');

    modifyBtn.addEventListener('click', () => {
        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);
        });
    });
}

const createBtn = document.getElementById('create-btn');

if (createBtn) {
    createBtn.addEventListener('click', () => {
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('등록이 완료되었습니다.');
            location.replace('/articles');
        });
    });
}

const deleteBtn = document.getElementById('delete-btn');

if (deleteBtn) {
    const id = location.pathname.split('/').pop();

    deleteBtn.addEventListener('click', () => {
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        }).then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        });
    });
}
