# language: pt
# charset: UTF-8

@PlanId=5
@SuiteId=9
Funcionalidade: Login
   Eu como cliente gostaria de acessar o sistema via login somente com credenciais validas

  @TestId=7
  @smoke
  @funcional
  @aceitacao
   Cenario: CT001 - Login - Executar login com valido
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias validas
    Entao sera apresentado a tela do menu principal

  @TestId=23
  @smoke
  @funcional
  @aceitacao
  Cenario:  CT002 - Login - Executar login com invalido
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias invalidas
    Entao sera apresentado uma mensagem de erro