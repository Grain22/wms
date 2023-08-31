package org.grain;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
//        GitLabApi gitLabApi = new GitLabApi(host, token);
//        ProjectApi projectApi = gitLabApi.getProjectApi();
//        TagsApi tagsApi = gitLabApi.getTagsApi();
//        Project project = projectApi.getProject(project_id);
//        List<String> tagList = project.getTagList();
//        tagsApi.getTags(project_id).forEach(System.out::println);
//        Tag tag = tagsApi.getTags(project_id).get(0);
//        Commit commit = tag.getCommit();
//        System.out.println(commit);
//        String defaultBranch = project.getDefaultBranch();
//        System.out.println(String.join(",", tagList));
//        Project p2 = gitLabApi.getProjectApi().getProject(namespace, project_name);
//        List<String> t2 = p2.getTagList();
//        String id = commit.getId();
//        String path = gitLabApi.getRepositoryApi().getTree(project_id, gitLabApi.getRepositoryApi().getTree(project_id, "/", "20230615-prepare-release").get(1).getPath(), "20230615-prepare-release").get(1).getPath();
//        RepositoryFile file = gitLabApi.getRepositoryFileApi().getFile(project_id, path, tag.getName());
//        InputStream rawFile = gitLabApi.getRepositoryFileApi().getRawFile(project_id, tagsApi.getTags(project_id).get(0).getName(), path);
//        byte[] bytes = rawFile.readAllBytes();
//        String s = new String(bytes, StandardCharsets.UTF_8);
//        System.out.println(s);
//        gitLabApi.close();
    }
}