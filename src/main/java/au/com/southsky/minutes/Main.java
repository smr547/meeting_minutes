package au.com.southsky.minutes;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.itextpdf.html2pdf.HtmlConverter;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.kohsuke.github.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {
    Options o = new Options();
    JCommander c = new JCommander(o);
    c.setProgramName(Main.class.getSimpleName());

    try {
      c.parse(args);
    } catch (ParameterException e) {
      c.usage();
      System.exit(1);
    }

    GitHub h = GitHub.connect();
    GHUser user = h.getUser(o.username);
    GHRepository repo = user.getRepository(o.repositoryName);

    StringBuilder builder = new StringBuilder("<h1>All issues</h1><ul>");

    GHIssue issue = repo.getIssue(345);

    {
      builder.append("<h2>");
      builder.append(issue.getTitle());
      builder.append("</h2>");

      for (GHIssueComment comment : issue.getComments()) {
        String body = comment.getBody();
        if (!body.isEmpty()) {
          builder
              .append("<h3>")
              .append(comment.getUser().getName())
              .append(" commented on ")
              .append(comment.getCreatedAt())
              .append("</h3>");

          Parser mdParser = Parser.builder().build();
          Node mdDoc = mdParser.parse(body);
          HtmlRenderer renderer = HtmlRenderer.builder().build();
          builder.append(renderer.render(mdDoc));
        }
      }
    }

    builder.append("</ul>");

    try (FileOutputStream pdfFile = new FileOutputStream("/tmp/out.pdf")) {
      HtmlConverter.convertToPdf(builder.toString(), pdfFile);
    }
  }

  public static class Options {
    @Parameter(
      required = true,
      names = {"-u", "--username"},
      description = "The GitHub username"
    )
    String username;

    @Parameter(
      required = true,
      names = {"-r", "--repo"},
      description = "The GitHub repo"
    )
    String repositoryName;
  }
}
