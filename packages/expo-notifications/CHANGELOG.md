# Changelog

## master

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Added a macro check for `UNLocationNotificationTrigger` to make this module compatible with Mac Catalyst ([#8171](https://github.com/expo/expo/pull/8171) by [@robertying](https://github.com/robertying))
- Fixed notification content text being truncated without the ability to expand the notification by adding [`BigTextStyle`](https://developer.android.com/reference/android/app/Notification.BigTextStyle) to all Android notifications, which allows them to be expanded and their content text fully viewed ([#8140](https://github.com/expo/expo/pull/8140) by [@thorbenprimke](https://github.com/thorbenprimke))

## [0.1.7] - 2020-05-05

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Fixed obsolete and invalid dependency on `>= @unimodules/core@5.1.1`, bringing backwards compatibility with older versions of `@unimodules/core` ([#8162](https://github.com/expo/expo/pull/8162) by [@sjchmiela](https://github.com/sjchmiela))

## [0.1.6] - 2020-05-05

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Fixed crash when serializing a notification containing a `null` value ([#8153](https://github.com/expo/expo/pull/8153) by [@sjchmiela](https://github.com/sjchmiela))
- Fixed a typo in `AndroidImportance` enum (`DEEFAULT` is now deprecated in favor of `DEFAULT`) ([#8161](https://github.com/expo/expo/pull/8161) by [@trevorah](https://github.com/trevorah))

## [0.1.5] - 2020-05-05

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Fixed `SoundResolver` causing crash if the `sound` property is not defined or doesn't contain a `.` ([#8150](https://github.com/expo/expo/pull/8150) by [@sjchmiela](https://github.com/sjchmiela))

## [0.1.4] - 2020-05-04

### 🛠 Breaking changes

### 🎉 New features

- Added a native setting allowing you to use a custom notification icon for Android notifications ([#8035](https://github.com/expo/expo/pull/8035) by [@sjchmiela](https://github.com/sjchmiela))
- Added a native setting and a runtime option allowing you to use a custom notification color for Android notifications ([#8035](https://github.com/expo/expo/pull/8035) by [@sjchmiela](https://github.com/sjchmiela))

### 🐛 Bug fixes

- Fixed initial notification not being emitted to `NotificationResponse` listener on iOS ([#7958](https://github.com/expo/expo/pull/7958) by [@sjchmiela](https://github.com/sjchmiela))

## [0.1.3] - 2020-04-30

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Fixed custom notification sounds not being applied properly to notifications and channels ([#8036](https://github.com/expo/expo/pull/8036) by [@sjchmiela](https://github.com/sjchmiela))
- Fixed iOS rejecting the Promise to schedule a notification if `sound` is not empty or a boolean ([#8036](https://github.com/expo/expo/pull/8036) by [@sjchmiela](https://github.com/sjchmiela))

## [0.1.2] - 2020-04-21

### 🛠 Breaking changes

### 🎉 New features

### 🐛 Bug fixes

- Fixed interpretation of `Date` and `number` triggers when calling `scheduleNotificationAsync` on iOS ([#7942](https://github.com/expo/expo/pull/7942) by [@sjchmiela](https://github.com/sjchmiela))