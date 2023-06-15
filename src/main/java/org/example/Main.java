package org.example;

import com.spotify.github.v3.clients.GitHubClient;
import com.spotify.github.v3.clients.IssueClient;
import com.spotify.github.v3.clients.RepositoryClient;
import com.spotify.github.v3.repos.Repository;

import java.io.File;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int APP_ID = 0;// The app ID here
        int INSTALLATION_ID = 0;
        int ISSUE_NUMBER = 1;
        String KEY_PATH = "path-to-private-key.pem";

        final GitHubClient githubClient =
                GitHubClient.create(
                        URI.create("https://api.github.com/"),
                        new File(KEY_PATH),
                        APP_ID
                );

        final GitHubClient scopedClient = GitHubClient.scopeForInstallationId(githubClient, INSTALLATION_ID);

        RepositoryClient repositoryClient = scopedClient.createRepositoryClient("luchob", "test-gh-app");

        IssueClient issueClient = repositoryClient.createIssueClient();

        issueClient.createComment(ISSUE_NUMBER, "Hello from my GH App!").get();
    }
}