package entity

data class User(var id: Int, var name: String, var repoList: List<Repo>)