#language: pt
@users
Funcionalidade: Validar os usuarios cadastradas

  Esquema do Cenario: listar todas os usuarios cadastradas e validar
    Dado usuario logado
    Quando retorno da api for <STATUS_CODE>
    Entao quantidade de usuarios for <QUANTIDADES>

    Exemplos:
      | NUMERO | STATUS_CODE | QUANTIDADES |
      | 1      | 200         | 3           |



