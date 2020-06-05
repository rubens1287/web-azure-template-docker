# language: pt
# charset: UTF-8

@PlanId=1
@SuiteId=4
Funcionalidade: Login
   Eu como cliente gostaria de acessar o sistema via login somente com credenciais validas

  @TestId=6
  @smoke
  @aceitacao
   Cenario: Executar login com credenciais validas
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias validas
    Entao sera apresentado a tela do menu principal

  @TestId=7
  @funcional
  @aceitacao
  Cenario:  Executar login com credenciais invalido
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias invalidas
    Entao sera apresentado uma mensagem de erro