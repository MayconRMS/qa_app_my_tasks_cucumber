#language: pt
@myTasks
Funcionalidade: Validar as tarefas cadastradas

  Esquema do Cenario: listar todas as tarefas cadastradas e validar
    Dado usuario logado
    Quando retorno da api for <STATUS_CODE>
    Entao quantidade de tarefas for <QUANTIDADES>

    Exemplos:
      | NUMERO | STATUS_CODE | QUANTIDADES |
      | 1      | 200         | 3           |



