function SomenteNumero(e) {
  var tecla = (window.event) ? event.keyCode : e.which;
  if ((tecla > 47 && tecla < 58))
    return true;
  else
    return (tecla === 8 || tecla === 0);
}

function mascara(o, f) {
  objeto = o;
  funcao = f;
  setTimeout("executaMascara()", 1);
}

function executaMascara() {
  objeto.value = funcao(objeto.value);
}
function telefone(valor) {
  valor = valor.replace(/\D/g, "");
  valor = valor.replace(/^(\d\d)(\d)/g, "($1) $2");
  valor = valor.replace(/(\d{4})(\d)/, "$1 - $2");
  return valor;
}

function cpf(valor) {
  valor = valor.replace(/\D/g, "");
  valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
  valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
  valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
  return valor;
}

function cep(valor) {
  valor = valor.replace(/\D/g, "");
  valor = valor.replace(/(\d{2})(\d)/, "$1.$2");
  valor = valor.replace(/(\d{3})(\d{1,3})$/, "$1-$2");
  return valor;
}

function cnpj(valor) {
  valor = valor.replace(/\D/g, "");
  valor = valor.replace(/^(\d{2})(\d)/, "$1.$2");
  valor = valor.replace(/^(\d{2}).(\d{3})(\d)/, "$1.$2.$3");
  valor = valor.replace(/.(\d{3})(\d)/, ".$1/$2");
  valor = valor.replace(/(\d{4})(\d)/, "$1-$2");
  return valor;
}
function moeda(valor) {
  valor = valor.replace(/\D/g, "");
  valor = valor.replace(/(\d)(\d{8})$/, "$1.$2");
  valor = valor.replace(/(\d)(\d{5})$/, "$1.$2");
  valor = valor.replace(/(\d)(\d{2})$/, "$1,$2");
  return valor;
} 