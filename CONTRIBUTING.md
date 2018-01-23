# Contributing
CryptoDash is released under the [MIT](http://opensource.org/licenses/MIT) license. 
If you would like to contribute to the project, this document should help you get started.

## Contributor workflow
* Try taking on of the project [issues](https://github.com/ljardim/cryptodash/issues). If you pick up an issue, please leave a comment stating that you are 
doing so. If you do not continue working on the issue, or you are taking a long time to complete it, please unassign it by leaving a comment on the issue.

[GitHub Flow](https://guides.github.com/introduction/flow/) is the branching model used. 

When contributing a new feature or fix:

  1. [Fork](https://help.github.com/articles/fork-a-repo/) the repo.
  2. Clone your forked copy of the repo
  
  ```
  git clone https://github.com/<your-id>/cryptodash.git
  ```
  
  3. Add this upstream repository as a remote
  
  ```
  git remote add upstream https://github.com/ljardim/cryptodash.git
  ```
  
  Make sure you keep your forked repo [up-to-date](https://help.github.com/articles/syncing-a-fork/) with the upstream repository.
  
  4. Create a branch off of your cloned fork. [How to](https://git-scm.com/docs/git-checkout).
  
  ```
  cd cryptodash
  git checkout -b meaningful-branch-name
  ```
  5. Add some changes or fix an issue.

  Commit to that branch locally, and regularly push your work to the same branch on the server.

  Commit messages must have a short description no longer than 50 characters followed by a blank line and a longer,
  more descriptive message that includes reference to issue(s) being addressed so that they will be automatically closed
  on a merge e.g. ```Closes #1234``` or ```Fixes #1234``` - see [here](https://help.github.com/articles/closing-issues-via-commit-messages/)
  for more details.
  
  When writing a commit message please follow [these conventions](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html).

  6. Pull Request (PR)

   Make sure any unit and integration tests still pass and create a [Pull Request](https://help.github.com/articles/using-pull-requests/) when you are ready 
   to submit your changes.

   _NOTE: If your PR does not merge cleanly, use ```git rebase master``` in your feature branch to update your pull 
   request rather than using ```git merge master```._

  7. Any code changes that affect documentation (e.g. README.MD) should be accompanied by corresponding changes
   (or additions) to the documentation and tests. This will ensure that if the merged PR is reversed, all traces of the
    change will be reversed as well.

After your Pull Request (PR) has been reviewed and signed off, a maintainer will merge it into the master branch.

### Code conventions and housekeeping

These convention should be followed:

* Make sure all new `.java` files to have a simple Javadoc class comment with at least an
  `@author` tag identifying you, and preferably at least a paragraph on what the class is for.
* Add the MIT license header comment to all new `.java` files - copy from existing files in the project.
* All code changes should be accompanied by new or modified tests.
* Add yourself as an `@author` to the `.java` files that you modify substantially (more than cosmetic changes).
* Add some Javadocs.
* In general commits should be atomic and diffs should be easy to read. For this reason do not mix any formatting fixes 
  or code moves with actual code changes.
  
## Reporting bugs and suggesting enhancements
If you find a bug, please submit an [issue](https://github.com/ljardim/cryptodash/issues). Try to provide enough information
for someone else to reproduce it. Submit an issue if you want to create a new feature or enhance an
existing one.

One of the project's maintainers should respond to your issue within 48 hours... if not, bump the issue and request that it be reviewed.