# Devops を成立させるもの

- DevOps を理解する上で最も重要なことは、これは組織的、ヒューマンイシューであり、技術的なイシューではないということ

- “improving the quality of your software by speeding up release cycles with cloud automation and practices, with the added benefit of software that actually stays up in production” (The Regis‐ ter).

## The influential DevOps writer John Willis has identified four key pillars
- culture, automation, measurement, and sharing (CAMS)

## Brian Dawson has called the DevOps trinity
- people and culture, process and practice, and tools and technology.



## Jordan Bach (AppDynamics)
With DevOps, much of the traditional IT operations work happens 
before code reaches production. 

Every release includes monitoring, logging, and A/B testing.

CI/CD pipelines automatically run unit tests, security scanners, and policy checks on every commit. 

Deployments are automatic.

Controls, tasks, and non-functional requirements are now implemented 
before release 
instead of during the frenzy and aftermath of a critical outage.



## Adopting DevOps requires a profound cultural transformation for businesses, which needs to start at the executive, strategic level, and propagate gradually to every part of the organization. Speed, agility, collaboration, automation, and software quality are key goals of DevOps, and for many companies that means a major shift in mindset.

## But DevOps works, and studies regularly suggest that companies that adopt DevOps principles release better software faster, react better and faster to failures and prob‐ lems, are more agile in the marketplace, and dramatically improve the quality of their products:


# DevOps の目的は開発をスピードアップすることである
the aim of DevOps is to speed up development teams, not slow them down with unnecessary and redundant work.


## DPE
Yes, a large part of traditional operations can and should be devolved to other teams, primarily those that involve code deployment and responding to code-related inci‐ dents. But to enable that to happen, there needs to be a strong central team building and supporting the DevOps ecosystem in which all the other teams operate.
Instead of calling this team operations, we like the name developer productivity engi‐ neering (DPE). DPE teams do whatever’s necessary to help developers do their work better and faster: operating infrastructure, building tools, busting problems.

And while developer productivity engineering remains a specialist skill set, the engi‐ neers themselves may move outward into the organization to bring that expertise where it’s needed.
Lyft engineer Matt Klein has suggested that, while a pure DevOps model makes sense for startups and small firms, as an organization grows, there is a natural tendency for infrastructure and reliability experts to gravitate toward a central team. But he says that team can’t be scaled indefinitely:
By the time an engineering organization reaches ~75 people, there is almost certainly a central infrastructure team in place starting to build common substrate features required by product teams building microservices. But there comes a point at which the central infrastructure team can no longer both continue to build and operate the infrastructure critical to business success, while also maintaining the support burden of helping product teams with operational tasks.
—Matt Klein
At this point, not every developer can be an infrastructure expert, just as a single team of infrastructure experts can’t service an ever-growing number of developers. For larger organizations, while a central infrastructure team is still needed, there’s also a case for embedding site reliability engineers (SREs) into each development or prod‐ uct team. They bring their expertise to each team as consultants, and also form a bridge between product development and infrastructure operations.

