Meta: @RP-01
Meta: @run

Scenario: Launch details are displayed

Given user opens the Report Portal
When user enters login and password
And user clicks the 'Login' button
And user opens the Project selector
And user selects the [yanina_shokun_personal] project
And user switches to the 'Launches' tab
Then 'Launches' page is opened
And the [<Launch_number>] launch with [<Launch_name>] name is displayed
And the [<Total>] total number of launches is displayed for [<Launch_number>] launch with [<Launch_name>] name
And the [<Passed>] passed number of launches is displayed for [<Launch_number>] launch with [<Launch_name>] name
And the [<Failed>] failed number of launches is displayed for [<Launch_number>] launch with [<Launch_name>] name
And the [<Skipped>] skipped number of launches is displayed for [<Launch_number>] launch with [<Launch_name>] name
When user clicks the 'User icon' on the Sidebar
And user clicks the [LOGOUT] item in the menu
Then 'Login' page is opened

Examples:
|Launch_name   |Launch_number|Total|Passed|Failed|Skipped|
|Demo Api Tests|#10          |30   |30    |EMPTY |EMPTY  |
|Demo Api Tests|#9           |25   |20    |5     |EMPTY  |
|Demo Api Tests|#8           |20   |10    |8     |2      |
|Demo Api Tests|#7           |15   |5     |9     |1      |
|Demo Api Tests|#6           |10   |1     |9     |EMPTY  |

Scenario: Launch platform and build details are displayed

Given user opens the Report Portal
When user enters login and password
And user clicks the 'Login' button
And user opens the Project selector
And user selects the [yanina_shokun_personal] project
And user switches to the 'Launches' tab
Then 'Launches' page is opened
And [<Platform>] platform attribute is displayed for [<Launch_number>] launch with [<Launch_name>] name
And [<Build>] build attribute is displayed for [<Launch_number>] launch with [<Launch_name>] name
When user clicks the 'User icon' on the Sidebar
And user clicks the [LOGOUT] item in the menu
Then 'Login' page is opened

Examples:
|Launch_name   |Launch_number|Platform      |Build        |
|Demo Api Tests|#10          |macos         |3.11.14.32.51|
|Demo Api Tests|#9           |windows 10    |3.11.14.32.48|
|Demo Api Tests|#8           |windows mobile|3.11.14.32.45|
|Demo Api Tests|#7           |ios           |3.11.14.32.41|
|Demo Api Tests|#6           |windows server|3.11.14.32.39|

Scenario: Launch checkbox behavior

Given user opens the Report Portal
When user enters login and password
And user clicks the 'Login' button
And user opens the Project selector
And user selects the [yanina_shokun_personal] project
And user switches to the 'Launches' tab
Then 'Launches' page is opened
When user checks the checkbox for [<Launch_number>] launch with [<Launch_name>] name
Then [<Total_launch_name>] launch is displayed in the 'Selected Items' toolbar
And [<Launch_number>] launch with [<Launch_name>] name is [checked]
When user unchecks the checkbox for [<Launch_number>] launch with [<Launch_name>] name
Then 'Selected Items' toolbar is not visible
And [<Launch_number>] launch with [<Launch_name>] name is [unchecked]
When user clicks the 'User icon' on the Sidebar
And user clicks the [LOGOUT] item in the menu
Then 'Login' page is opened

Examples:
|Launch_name   |Launch_number|Total_launch_name |
|Demo Api Tests|#10          |Demo Api Tests #10|
|Demo Api Tests|#9           |Demo Api Tests #9 |
|Demo Api Tests|#8           |Demo Api Tests #8 |
|Demo Api Tests|#7           |Demo Api Tests #7 |
|Demo Api Tests|#6           |Demo Api Tests #6 |
