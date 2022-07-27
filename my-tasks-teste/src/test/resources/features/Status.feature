#language: pt
@status
Funcionalidade: Alterar status das tarefas

  Esquema do Cenario: alterar status das tarefas
    Dado usuario logado
    Quando retorno da api for <STATUS_CODE>
    Entao quantidade de tarefas for <QUANTIDADES>

    Exemplos:
      | NUMERO | STATUS_CODE | QUANTIDADES |
      | 1      | 200         | 3           |



