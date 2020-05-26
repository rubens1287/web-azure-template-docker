# language: pt
# charset: UTF-8

@PlanId=5
@SuiteId=26
Funcionalidade: Login
   Eu como cliente gostaria de acessar o sistema via login somente com credenciais validas

  @TestId=7
  @smoke
  @aceitacao
   Cenario: Executar login com credenciais validas
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias validas
    Entao sera apresentado a tela do menu principal

  @TestId=23
  @funcional
  @aceitacao
  Cenario:  Executar login com credenciais invalido
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias invalidas
    Entao sera apresentado uma mensagem de erro