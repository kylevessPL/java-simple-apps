import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.channels.*;

public class zad4 {
	static final int NUMBER_OF_CHARACTERS = 1000;
	
	static final int FIRST_ASCII_CHAR = 32;
	static final int LAST_ASCII_CHAR = 126;

	public static void main(String[] args) {
		Random rand = new Random();
		char[] tab = new char[NUMBER_OF_CHARACTERS];
		for(int i = 0; i < NUMBER_OF_CHARACTERS; ++i)
			tab[i] = (char)(rand.nextInt(LAST_ASCII_CHAR - FIRST_ASCII_CHAR + 1) + FIRST_ASCII_CHAR);
		job("java.io", "plik1.txt", new IOFile(), tab);
		job("java.nio", "plik2.txt", new NIOFile(), tab);
	}
	
	static void job(String n, String f, FileOperation p, char[] s) {
		try {
			long wrt0 = System.nanoTime();
			p.save(f, s);
			long wrt1 = System.nanoTime();
			System.out.println(n + ": Write time: " + (wrt1 - wrt0) / 1000000 + "ms");
			long rdt0 = System.nanoTime();
			char[] s2 = p.read(f);
			long rdt1 = System.nanoTime();
			System.out.println(n + ": Read time: " + (rdt1 - rdt0) / 1000000 + "ms");
			System.out.println();
			System.out.println(s2);
			System.out.println();
		}
		catch(Exception e) {
			System.out.println(n + ": an error occured: " + e.getLocalizedMessage());
		}
	}
}

interface FileOperation {
	void save(String f, char[] s) throws Exception;
	char[] read(String f) throws Exception;
}

class IOFile implements FileOperation {
	@Override
	public void save(String f, char[] s) throws Exception {
		try(OutputStream out = new FileOutputStream(f)) {
			try(Writer wr = new OutputStreamWriter(out)) {
				wr.write(s);
			}
		}
	}

	@Override
	public char[] read(String f) throws Exception {
		File file = new File(f);
		long len = file.length();
		char[] t = new char[(int)len];
		try(InputStream in = new FileInputStream(file)) {
			try(Reader rd = new InputStreamReader(in)) {
				rd.read(t);
			}
		}
		return t;
	}
}

class NIOFile implements FileOperation {
	@Override
	public void save(String f, char[] s) throws Exception {
		Path path = Paths.get(f);
		try(FileChannel fch = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			ByteBuffer bbuf = ByteBuffer.allocate(s.length * 2);
			CharBuffer cbuf = bbuf.asCharBuffer();
			cbuf.put(s);
			fch.write(bbuf);
		}
	}

	@Override
	public char[] read(String f) throws Exception {
		Path path = Paths.get(f);
		CharBuffer cbuf;
		try(FileChannel fch = FileChannel.open(path, StandardOpenOption.READ)) {
			ByteBuffer bbuf = ByteBuffer.allocate((int) fch.size());
			cbuf = bbuf.asCharBuffer();
			fch.read(bbuf);
		}
		char[] t = new char[cbuf.length()];
		cbuf.get(t);
		return t;
	}
}