import java.io.*;
import java.util.*;

public class Main {

	public static int n, m;
	public static Set<Integer> setN = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			try {
				n = Integer.parseInt(br.readLine());
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 0; i < n; i++) {	
					setN.add(Integer.parseInt(st.nextToken()));
				}
				
				m = Integer.parseInt(br.readLine());
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < m; i++) {
					bw.write(String.valueOf(setN.contains(Integer.parseInt(st.nextToken())) ? 1 : 0));
					bw.newLine();
				}
			} catch (Exception e) {
				break;
			}
		}
		bw.flush();
		bw.close();
	}
}
