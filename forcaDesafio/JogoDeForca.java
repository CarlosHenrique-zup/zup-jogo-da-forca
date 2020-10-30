package br.com.zup.forcaDesafio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.Normalizer;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JogoDeForca {

	private static final String ESPACAMENTO = " ";
	private static final String PULALINHA = "\n";
	private static final String DETALHE_PIPE = "|";
	private static final String DETALHE = "====================================";
	private static final String PALAVRA_REVELADA = "A PALAVRA ERA = ";
	private static final String TENTE_NOVAMENTE = "TENTE NOVAMENTE!";
	private static final String MENSAGEM_DE_PARABENS = "PARABÉNS, VOCÊ ACERTOU!";
	private static final String MENSAGEM_DE_ERRO = "VOCÊ ERROU!";
	private static final String MAIS_DE_UMA_LETRA_DIGITADA = "|   [VOCÊ DIGITOU MAIS DE UMA LETRA!]   |";
	private static final String LETRA_ACERTADA = "PARABÉNS! VOCÊ ACERTOU A LETRA!";
	private static final String MENSAGEM_DE_TENTATIVAS_DE_ERRO = "VOCÊ ERROU PELA - ";
	private static final String FIM_DO_JOGO = "|           [FIM DO JOGO]         |";
	private static final String NOME_DO_ARQUIVO = "palavras.txt";

	public static void tituloDeApresentacao() {
		System.out.println(DETALHE);
		System.out.println("|           [BEM-VINDO]           |");
		System.out.println(DETALHE);
		System.out.println("|              [AO]               |");
		System.out.println(DETALHE);
		System.out.println("|         [JOGO DA FORCA]         |");
		System.out.println(DETALHE);
	}

	public static void letraAcertada() {

		System.out.println(DETALHE);
		System.out.println(LETRA_ACERTADA);
		System.out.println(DETALHE);

	}

	public static void maisDeUmaLetraDigitada() {

		System.out.println(DETALHE);
		System.out.println(MAIS_DE_UMA_LETRA_DIGITADA);
		System.out.println(DETALHE);

	}

	public static void quantidadeDeTentativasFeitasPeloUsuario(int tentativas) {

		System.out.println(DETALHE);
		System.out.print(PULALINHA + MENSAGEM_DE_TENTATIVAS_DE_ERRO + tentativas + "º vez!");
		System.out.print(PULALINHA + TENTE_NOVAMENTE);
		System.out.println(PULALINHA + DETALHE);

	}

	public static int lerLinhasDoArquivo(int qtdPalavras) throws IOException {

		LineNumberReader lerLinhas = new LineNumberReader(new FileReader(NOME_DO_ARQUIVO));
		lerLinhas.skip(Long.MAX_VALUE);
		qtdPalavras = lerLinhas.getLineNumber() + 1;
		lerLinhas.close();

		return qtdPalavras;

	}

	public static void palavraSorteada(String palavrasSorteadas) {

		System.out.print(PULALINHA);
		for (int y = 0; y < palavrasSorteadas.length(); y++) {
			System.out.print(" _ ");
		}

	}

	public static void interacaoDoUsuarioComPrograma() {

		System.out.print(PULALINHA + "USUÁRIO,");
		System.out.print(PULALINHA + "DIGITE UMA LETRA = ");

	}

	public static String trocaLetrasAcentuadas(String palavra) {

		String nfdNormalizedString = Normalizer.normalize(palavra, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");

	}

	public static String adicionarPalavra(String palavra) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_DO_ARQUIVO, true));
		writer.append(palavra);
		writer.close();
		return palavra;

	}

	public static String[] leituraDoArquivo(String[] palavraLida) throws IOException {

		BufferedReader leituraDoArquivoTxt = new BufferedReader(new FileReader(NOME_DO_ARQUIVO));
		String linhaLida;
		int primeiraLinhaASerLida = 0;
		while ((linhaLida = leituraDoArquivoTxt.readLine()) != null) {
			palavraLida[primeiraLinhaASerLida] = linhaLida;
			primeiraLinhaASerLida++;
		}

		leituraDoArquivoTxt.close();
		return palavraLida;
	}

	public static void comparacaoDaLetraDigitadaEPalavra(String palavraSorteada, char letraEscolhida,
			char procuraLetra[], boolean contTentativas) {

		for (int i = 0; i < palavraSorteada.length(); i++) {
			if (letraEscolhida == palavraSorteada.charAt(i)) {
				procuraLetra[i] = 1;
				contTentativas = false;
			}
		}
	}

	public static boolean substituiUnderlinePelaLetra(char[] procuraLetra, boolean acertos, String palavraSorteada) {

		acertos = true;
		for (int i = 0; i < palavraSorteada.length(); i++) {
			if (procuraLetra[i] == 0) {
				System.out.print(" _ ");
				acertos = false;
			} else {
				System.out.print(ESPACAMENTO + palavraSorteada.charAt(i) + ESPACAMENTO);
			}
		}

		return acertos;
	}

	public static void letrasErradasUtilizadasPeloUsuario(String letrasUsadasPeloUsuario,
			char letraEscolhidaPeloUsuario) {

		System.out.println(PULALINHA + DETALHE);
		System.out.printf("LETRAS DIGITAS - %s", letrasUsadasPeloUsuario += letraEscolhidaPeloUsuario + ESPACAMENTO);
		System.out.println(PULALINHA + DETALHE);

	}

	public static void inserirPalavraNoArquivo(Scanner sc) throws IOException {

		System.out.print(PULALINHA + "DIGITE UMA PALAVRA NO ARQUIVO: ");
		String palavraAdicionada = sc.next();
		adicionarPalavra(PULALINHA + palavraAdicionada);
		System.out.println("PALAVRA ADICIONADA: " + adicionarPalavra(palavraAdicionada));

	}

	public static String vencedorOuPerdedorDoJogo(Scanner sc, String palavraSorteada, String letraDoUsuario)
			throws IOException {

		if (letraDoUsuario.contains(palavraSorteada)) {

			System.out.print(PULALINHA + MENSAGEM_DE_PARABENS);
			System.out.print(PULALINHA + PALAVRA_REVELADA + DETALHE_PIPE + palavraSorteada + DETALHE_PIPE);
			System.out.println(PULALINHA + DETALHE);

			inserirPalavraNoArquivo(sc);

		} else {
			System.out.print(PULALINHA + MENSAGEM_DE_ERRO);
			System.out.print(PULALINHA + PALAVRA_REVELADA + DETALHE_PIPE + palavraSorteada + DETALHE_PIPE);
			System.out.println(PULALINHA + DETALHE);
		}

		return letraDoUsuario;
	}

	public static void novaTentativaParaUsuario() {
		System.out.println("|                                  |");
		System.out.println("|          [TENTE NOVAMENTE]       |");
		System.out.println("|                                  |");
		System.out.println("|            [1] - SIM             |");
		System.out.println("|            [0] - NÃO             |");
		System.out.println("|                                  |");
		System.out.println(DETALHE);
		System.out.print("QUER CONTINUAR ? - ");
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		Random random = new Random();

		String letraDoUsuario = ESPACAMENTO;
		String letraInserida = ESPACAMENTO;
		char letraEscolhidaPeloUsuario = ' ';
		int tentarNovamente = 0;
		String letrasUsadasPeloUsuario = ESPACAMENTO;
		int quantidadeDePalavras = 0;

		tituloDeApresentacao();

		do {

			String[] palavraLida = new String[lerLinhasDoArquivo(quantidadeDePalavras)];

			leituraDoArquivo(palavraLida);

			int indiceEscolhido = random.nextInt(lerLinhasDoArquivo(quantidadeDePalavras));
			String palavraSorteada = palavraLida[indiceEscolhido];
			char[] procuraLetra = new char[palavraSorteada.length()];

			int tentativas = 0;
			boolean acertos = false;

			palavraSorteada(palavraSorteada);

			System.out.print(PULALINHA);
			System.out.print(DETALHE);

			do {

				interacaoDoUsuarioComPrograma();
				letraInserida = sc.next().toLowerCase();
				String mudancaDaLetraSemAcento = trocaLetrasAcentuadas(letraInserida);

				if (mudancaDaLetraSemAcento.length() > 1) {
					maisDeUmaLetraDigitada();
					break;
				} else {
					letraEscolhidaPeloUsuario = letraInserida.charAt(0);

					boolean contTentativas = true;

					comparacaoDaLetraDigitadaEPalavra(palavraSorteada, letraEscolhidaPeloUsuario, procuraLetra,
							contTentativas);

					if (contTentativas) {
						tentativas++;
						quantidadeDeTentativasFeitasPeloUsuario(tentativas);
					} else {
						letraAcertada();
					}
				}

				System.out.println(PULALINHA);

				substituiUnderlinePelaLetra(procuraLetra, acertos, palavraSorteada);
				letrasErradasUtilizadasPeloUsuario(letrasUsadasPeloUsuario, letraEscolhidaPeloUsuario);

			} while (!acertos && tentativas <= 5);

			vencedorOuPerdedorDoJogo(sc, palavraSorteada, letraDoUsuario);

			novaTentativaParaUsuario();
			tentarNovamente = sc.nextInt();

		} while (tentarNovamente != 0);

		System.out.println(DETALHE);
		System.out.println(FIM_DO_JOGO);
		System.out.println(DETALHE);

		sc.close();
	}
}