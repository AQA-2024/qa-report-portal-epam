**RP E2E tests** (qa-report-portal)
-----

This project contains End-To-End tests for Report Portal.

They use JBehave + Selenide.

Tests consist on **stories** located at *src\main\resources\stories* which may contain several **scenarios** for different user-criteria.<br/>
By convention, meta-tags are used to indicate corresponding user-story under test, example:

    Meta: @RP-01
    Scenario: ...

Scenarios are made of **steps** following given-when-then convention and corresponding predicates.<br/>

# Option

(see [TestParameters.java](src\main\java\com\epam\qa\reportportal\utils\TestParameters.java)):

    -Dqaa.metaFilters=<filters>
    

Where

- *filters* allow to specify several meta-tags to filter by.<br/>
  Tags must be prefixed with `+` to filter in only tests with that tag or `-` to exclude those.
  By default: `"-make-screenshot -ignore -ignoreUsualRun -skip"`<br/>
  Example: `"+run -ignore"` will run only tests marked with `@run` tag in Meta section, ignoring those marked with `@ignore`:

```
    Meta: @RP-01
          @run
    Scenario: ...
```

**Example:**

    -Dqaa.metaFilters="+run -ignore"
