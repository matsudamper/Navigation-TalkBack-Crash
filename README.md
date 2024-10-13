# Navigation TalkBack Crash

## Environment
Version confirmed by emulator.
- 9.0(no Crash)  
- 8.1(no Crash)  
- 8.0(crash)  
- 7.1.1(crash)  

Set up Bitwarden, TalkBack, etc.
<table>
<tr>
<td>BitWaden</td>
<td>TalkBack</td>
</tr>
<tr>
<td><img width="300" src="README/bitwaden.png"></td>
<td><img width="300" src="README/talkback.png"></td>
</tr>
</table>

## Step
- Handle Focus with AndroidView inside Compose.  
- call popBackStack or navigate.  
- Crash.  

## StackTrace
<details>
<summary>navigate StackTrace</summary>

```
Process: net.matsudamper.talkbackcrash, PID: 5482
java.lang.IllegalStateException: LayoutCoordinate operations are only valid when isAttached is true
	at androidx.compose.ui.node.NodeCoordinator.getParentLayoutCoordinates(NodeCoordinator.kt:277)
	at androidx.compose.ui.layout.LayoutCoordinatesKt.findRootCoordinates(LayoutCoordinates.kt:183)
	at androidx.compose.ui.node.NodeCoordinator.touchBoundsInRoot(NodeCoordinator.kt:730)
	at androidx.compose.ui.node.SemanticsModifierNodeKt.touchBoundsInRoot(SemanticsModifierNode.kt:71)
	at androidx.compose.ui.semantics.SemanticsNode.getTouchBoundsInRoot(SemanticsNode.kt:109)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(AndroidComposeViewAccessibilityDelegateCompat.android.kt:3077)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(AndroidComposeViewAccessibilityDelegateCompat.android.kt:3096)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap(AndroidComposeViewAccessibilityDelegateCompat.android.kt:3127)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes(AndroidComposeViewAccessibilityDelegateCompat.android.kt:354)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.createNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.android.kt:485)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$createNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.android.kt:189)
	at androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$MyNodeProvider.createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.android.kt:2867)
	at android.view.ViewRootImpl.handleWindowContentChangedEvent(ViewRootImpl.java:7180)
	at android.view.ViewRootImpl.requestSendAccessibilityEvent(ViewRootImpl.java:7110)
	at android.view.View.sendAccessibilityEventUncheckedInternal(View.java:7061)
	at android.view.View.sendAccessibilityEventUnchecked(View.java:7038)
	at android.view.ViewRootImpl$SendWindowContentChangedAccessibilityEvent.run(ViewRootImpl.java:7890)
	at android.view.ViewRootImpl$SendWindowContentChangedAccessibilityEvent.runOrPost(ViewRootImpl.java:7916)
	at android.view.ViewRootImpl.postSendWindowContentChangedCallback(ViewRootImpl.java:7028)
	at android.view.ViewRootImpl.notifySubtreeAccessibilityStateChanged(ViewRootImpl.java:7203)
	at android.view.View.notifySubtreeAccessibilityStateChangedIfNeeded(View.java:11197)
	at android.view.ViewGroup.notifySubtreeAccessibilityStateChangedIfNeeded(ViewGroup.java:3595)
	at android.view.ViewGroup.notifySubtreeAccessibilityStateChangedIfNeeded(ViewGroup.java:3591)
	at android.view.View.setFrame(View.java:19709)
	at android.view.View.layout(View.java:19587)
	at android.view.ViewGroup.layout(ViewGroup.java:6053)
	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
	at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
	at android.view.View.layout(View.java:19590)
	at android.view.ViewGroup.layout(ViewGroup.java:6053)
	at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1791)
	at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1635)
	at android.widget.LinearLayout.onLayout(LinearLayout.java:1544)
	at android.view.View.layout(View.java:19590)
	at android.view.ViewGroup.layout(ViewGroup.java:6053)
	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
	at android.widget.FrameLayout.onLayout(FrameLayout.java:261)
	at com.android.internal.policy.DecorView.onLayout(DecorView.java:758)
	at android.view.View.layout(View.java:19590)
	at android.view.ViewGroup.layout(ViewGroup.java:6053)
	at android.view.ViewRootImpl.performLayout(ViewRootImpl.java:2484)
	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2200)
	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1386)
	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6733)
	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:911)
	at android.view.Choreographer.doCallbacks(Choreographer.java:723)
	at android.view.Choreographer.doFrame(Choreographer.java:658)
	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:897)
	at android.os.Handler.handleCallback(Handler.java:789)
	at android.os.Handler.dispatchMessage(Handler.java:98)
	at android.os.Looper.loop(Looper.java:164)
	at android.app.ActivityThread.main(ActivityThread.java:6541)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:767)
```
</details>

If you popBackStack from a screen with Focus, you will get a different error.
<details>
<summary>popBackStack StackTrace</summary>
  
```
Process: net.matsudamper.talkbackcrash, PID: 7922
java.lang.IllegalStateException: LayoutNode should be attached to an owner
	at androidx.compose.ui.node.LayoutNodeKt.requireOwner(LayoutNode.kt:1490)
	at androidx.compose.ui.node.NodeCoordinator.getSnapshotObserver(NodeCoordinator.kt:293)
	at androidx.compose.ui.node.NodeCoordinator.invoke(NodeCoordinator.kt:395)
	at androidx.compose.ui.node.NodeCoordinator.invoke(NodeCoordinator.kt:58)
	at androidx.compose.ui.platform.RenderNodeApi23.record(RenderNodeApi23.android.kt:280)
	at androidx.compose.ui.platform.RenderNodeLayer.updateDisplayList(RenderNodeLayer.android.kt:301)
	at androidx.compose.ui.platform.AndroidComposeView.dispatchDraw(AndroidComposeView.android.kt:1046)
	at android.view.View.draw(View.java:19126)
	at android.view.View.updateDisplayListIfDirty(View.java:18073)
	at android.view.View.draw(View.java:18851)
	at android.view.ViewGroup.drawChild(ViewGroup.java:4214)
	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4000)
	at android.view.View.updateDisplayListIfDirty(View.java:18064)
	at android.view.View.draw(View.java:18851)
	at android.view.ViewGroup.drawChild(ViewGroup.java:4214)
	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4000)
	at android.view.View.updateDisplayListIfDirty(View.java:18064)
	at android.view.View.draw(View.java:18851)
	at android.view.ViewGroup.drawChild(ViewGroup.java:4214)
	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4000)
	at android.view.View.updateDisplayListIfDirty(View.java:18064)
	at android.view.View.draw(View.java:18851)
	at android.view.ViewGroup.drawChild(ViewGroup.java:4214)
	at android.view.ViewGroup.dispatchDraw(ViewGroup.java:4000)
	at android.view.View.draw(View.java:19126)
	at com.android.internal.policy.DecorView.draw(DecorView.java:785)
	at android.view.View.updateDisplayListIfDirty(View.java:18073)
	at android.view.ThreadedRenderer.updateViewTreeDisplayList(ThreadedRenderer.java:643)
	at android.view.ThreadedRenderer.updateRootDisplayList(ThreadedRenderer.java:649)
	at android.view.ThreadedRenderer.draw(ThreadedRenderer.java:757)
	at android.view.ViewRootImpl.draw(ViewRootImpl.java:2980)
	at android.view.ViewRootImpl.performDraw(ViewRootImpl.java:2794)
	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2347)
	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1386)
	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6733)
	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:911)
	at android.view.Choreographer.doCallbacks(Choreographer.java:723)
	at android.view.Choreographer.doFrame(Choreographer.java:658)
	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:897)
	at android.os.Handler.handleCallback(Handler.java:789)
	at android.os.Handler.dispatchMessage(Handler.java:98)
	at android.os.Looper.loop(Looper.java:164)
	at android.app.ActivityThread.main(ActivityThread.java:6541)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:767)
```
</details>
