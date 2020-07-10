# language: pt
# charset: UTF-8

@Plan_Id=1
@Des_Suite_Id=4
@Qa_Suite_Id=4
@Hom_Suite_Id=4
Funcionalidade: Login
   Eu como cliente gostaria de acessar o sistema via login somente com credenciais validas

  @Test_Id=6
   Cenario: Executar login com credenciais validas
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias validas
    Entao sera apresentado a tela do menu principal

  @Test_Id=7
  @funcional
  @aceitacao
  Cenario:  Executar login com credenciais invalido
    Dado eu estou na pagina de login
    Quando eu efetuar o login com credencias invalidas
    Entao sera apresentado uma mensagem de erro