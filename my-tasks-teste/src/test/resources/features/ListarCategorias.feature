#language: pt
@category
Funcionalidade: Validar as categorias cadastradas

  Esquema do Cenario: listar todas as categorias cadastradas e validar
    Dado usuario logado
    Quando retorno da api for <STATUS_CODE>
    Entao quantidade de categorias for <QUANTIDADES>

    Exemplos:
      | NUMERO | STATUS_CODE | QUANTIDADES |
      | 1      | 200         | 3           |



