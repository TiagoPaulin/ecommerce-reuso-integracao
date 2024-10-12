function showImagePreview(input) {
    const file = input.files[0];
    const previewImage = input.previousElementSibling;


    if (file) {
        const reader = new FileReader();

        reader.onload = function (e) {
            previewImage.setAttribute("src", e.target.result )
        };

        reader.readAsDataURL(file);
    }
}

// Adiciona event listener para cada input do tipo file
document.querySelectorAll('input[type="file"]').forEach(function (fileInput) {
    fileInput.addEventListener('change', function () {
        showImagePreview(this);
    });
});