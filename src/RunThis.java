import java.io.IOException;

public class RunThis {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "java", "-jar", "Textengine.jar");
            pb.inheritIO(); // Redirige les entrées/sorties du processus enfant vers le terminal parent
            Process process = pb.start();
            process.waitFor(); // Attend la fin du processus
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
