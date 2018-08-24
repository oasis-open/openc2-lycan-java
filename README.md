<div>
<h1>README</h1>

## Lycan

[![Build Status](https://travis-ci.org/oasis-open/openc2-lycan-java.svg)](https://travis-ci.org/oasis-open/openc2-lycan-java)
[![Coverage Status](https://codecov.io/gh/oasis-open/openc2-lycan-java/branch/develop/graph/badge.svg)](https://codecov.io/gh/oasis-open/openc2-lycan-java)

Lycan is an implementation of the OpenC2 OASIS standard for command and control messaging. 
The current implementation is based on the Working Draft 06/Committee Specification Draft  04 documentation. 

## Requirements

Java 1.8 or later.
Jackson 2.8.8 or later.

## Installation

### Maven users

This repo is not currently in Maven Central so it requires users to manually build the repo
and push the resulting jar to your local Maven repository.


Add this dependency to your project's POM:

```xml
<dependency>
	<groupId>org.oasis.openc2</groupId>
	<artifactId>lycan</artifactId>
	<version>0.1.0</version>
</dependency>

```

### Others

You'll need to manually install the following JARs:

* Lycan https://github.com/<location/to/latest>
* Jackson core 2.8.8 or later
* Jackson databind 2.8.8 or later
* Jackson annotations 2.8.8 or later

## Documentation

Please see the [Java docs](https://github.com/oasis-open/openc2-lycan-java/doc) for the most up-to-date documentation.

## Usage

LycanExample.java

```java
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.target.DomainName;

public class LycanExample.java {

	public static void main(String[] args) {
		DomainName target = new DomainName("host.name.com");
		OpenC2Message message = new OpenC2Message(ActionType.QUERY, target);

		System.out.println(message.toJson());
		System.out.println(message.toPrettyJson());
	}
}
```

## Built with
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://github.com/oasis-open/openc2-lycan-java/blob/master/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/oasis-open/openc2-lycan-java/tags).

## Authors

* **Blake Essing** - *Initial work* - [AT&T](https://github.com/bessing)


<div>
<h2><a id="readme-general">OASIS TC Open Repository: openc2-lycan-java</a></h2>

<p>This GitHub public repository ( <b><a href="https://github.com/oasis-open/openc2-lycan-java">https://github.com/oasis-open/openc2-lycan-java</a></b> ) was created at the request of the <a href="https://www.oasis-open.org/committees/openc2/">OASIS Open Command and Control (OpenC2) TC</a> as an <a href="https://www.oasis-open.org/resources/open-repositories/">OASIS TC Open Repository</a> to support development of open source resources related to Technical Committee work.</p>

<p>While this TC Open Repository remains associated with the sponsor TC, its development priorities, leadership, intellectual property terms, participation rules, and other matters of governance are <a href="https://github.com/oasis-open/openc2-lycan-java/blob/master/CONTRIBUTING.md#governance-distinct-from-oasis-tc-process">separate and distinct</a> from the OASIS TC Process and related policies.</p>

<p>All contributions made to this TC Open Repository are subject to open source license terms expressed in the <a href="https://www.oasis-open.org/sites/www.oasis-open.org/files/MIT-License.txt">MIT License</a>.  That license was selected as the declared <a href="https://www.oasis-open.org/resources/open-repositories/licenses">"Applicable License"</a> when the TC Open Repository was created.</p>

<p>As documented in <a href="https://github.com/oasis-open/openc2-lycan-java/blob/master/CONTRIBUTING.md#public-participation-invited">"Public Participation Invited</a>", contributions to this OASIS TC Open Repository are invited from all parties, whether affiliated with OASIS or not.  Participants must have a GitHub account, but no fees or OASIS membership obligations are required.  Participation is expected to be consistent with the <a href="https://www.oasis-open.org/policies-guidelines/open-repositories">OASIS TC Open Repository Guidelines and Procedures</a>, the open source <a href="https://github.com/oasis-open/openc2-lycan-java/blob/master/LICENSE">LICENSE</a> designated for this particular repository, and the requirement for an <a href="https://www.oasis-open.org/resources/open-repositories/cla/individual-cla">Individual Contributor License Agreement</a> that governs intellectual property.</p>

</div>

<div>
<h2><a id="purposeStatement">Statement of Purpose</a></h2>

<p>Statement of Purpose for this OASIS TC Open Repository (openc2-lycan-java) as <a href="https://lists.oasis-open.org/archives/openc2/201802/msg00006.html">proposed</a> and <a href="https://lists.oasis-open.org/archives/openc2/201803/msg00007.html">approved</a> [<a href="https://lists.oasis-open.org/archives/openc2/201803/msg00023.html">bis</a>] by the TC:</p>

<p>The purpose of this OASIS TC Open repository is to develop and maintain a java implementation of <a href="http://docs.oasis-open.org/openc2/">OpenC2</a>, and to provide a java codebase to facilitate other prototype efforts.  The java library is designed to support transformations between data-interchange formats (such as JSON) and java language objects.</p>

<p>The OASIS OpenC2 Technical Committee was <a href="https://www.oasis-open.org/committees/openc2/charter.php">chartered</a> to address matters as they pertain to command and control of cyber defense technologies, and to maintain a library of prototype implementations.</p>

</div>

<div><h2><a id="purposeClarifications">Additions to Statement of Purpose</a></h2>

<p>Repository Maintainers may include here any clarifications &mdash; any additional sections, subsections, and paragraphs that the Maintainer(s) wish to add as descriptive text, reflecting (sub-) project status, milestones, releases, modifications to statement of purpose, etc.  The project Maintainers will create and maintain this content on behalf of the participants.</p>
</div>

<div>
<h2><a id="maintainers">Maintainers</a></h2>

<p>TC Open Repository <a href="https://www.oasis-open.org/resources/open-repositories/maintainers-guide">Maintainers</a> are responsible for oversight of this project's community development activities, including evaluation of GitHub <a href="https://github.com/oasis-open/openc2-lycan-java/blob/master/CONTRIBUTING.md#fork-and-pull-collaboration-model">pull requests</a> and <a href="https://www.oasis-open.org/policies-guidelines/open-repositories#repositoryManagement">preserving</a> open source principles of openness and fairness. Maintainers are recognized and trusted experts who serve to implement community goals and consensus design preferences.</p>

<p>Initially, the associated TC members have designated one or more persons to serve as Maintainer(s); subsequently, participating community members may select additional or substitute Maintainers, per <a href="https://www.oasis-open.org/resources/open-repositories/maintainers-guide#additionalMaintainers">consensus agreements</a>.</p>

<p><b><a id="currentMaintainers">Current Maintainers of this TC Open Repository</a></b></p>

<ul>
<li><a href="mailto:be772k@att.com">Blake Essing</a>; GitHub ID: <a href="https://github.com/be772k/">https://github.com/be772k/</a>; WWW: <a href="https://www.att.com/">AT&amp;T</a></li>
</ul>

</div>

<div><h2><a id="aboutOpenRepos">About OASIS TC Open Repositories</a></h2>

<p><ul>
<li><a href="https://www.oasis-open.org/resources/open-repositories/">TC Open Repositories: Overview and Resources</a></li>
<li><a href="https://www.oasis-open.org/resources/open-repositories/faq">Frequently Asked Questions</a></li>
<li><a href="https://www.oasis-open.org/resources/open-repositories/licenses">Open Source Licenses</a></li>
<li><a href="https://www.oasis-open.org/resources/open-repositories/cla">Contributor License Agreements (CLAs)</a></li>
<li><a href="https://www.oasis-open.org/resources/open-repositories/maintainers-guide">Maintainers' Guidelines and Agreement</a></li>
</ul></p>

</div>

<div><h2><a id="feedback">Feedback</a></h2>

<p>Questions or comments about this TC Open Repository's activities should be composed as GitHub issues or comments. If use of an issue/comment is not possible or appropriate, questions may be directed by email to the Maintainer(s) <a href="#currentMaintainers">listed above</a>.  Please send general questions about TC Open Repository participation to OASIS Staff at <a href="mailto:repository-admin@oasis-open.org">repository-admin@oasis-open.org</a> and any specific CLA-related questions to <a href="mailto:repository-cla@oasis-open.org">repository-cla@oasis-open.org</a>.</p>

</div></div>
