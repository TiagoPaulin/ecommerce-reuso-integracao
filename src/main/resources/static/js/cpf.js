// <input type="text" name="cpf" id="cpf" required spellcheck="false">


// Função para formatar CPF
function formatarCPF(input) {
    let value = input.value.replace(/\D/g, ''); // Remove caracteres não numéricos

    // Garante que o CPF tenha no máximo 11 dígitos
    value = value.substr(0, 11);

    if (value.length > 3) {
        value = value.replace(/^(\d{3})/, '$1.'); // Adiciona ponto após os primeiros 3 dígitos
    }
    if (value.length > 6) {
        value = value.replace(/^(\d{3})\.(\d{3})/, '$1.$2.'); // Adiciona ponto após os próximos 3 dígitos
    }
    if (value.length > 9) {
        value = value.replace(/^(\d{3})\.(\d{3})\.(\d{3})/, '$1.$2.$3-'); // Adiciona traço após os próximos 3 dígitos
    }

    // Remove pontos e traços caso o usuário esteja apagando
    if (input.oldValue && input.oldValue.length > value.length) {
        value = value.replace(/[.-]$/, '');
    }

    input.value = value;
    input.oldValue = input.value; // Armazena o valor atual para a próxima comparação
}


document.querySelector('.cpf').addEventListener('input', function() {
    formatarCPF(this);
});