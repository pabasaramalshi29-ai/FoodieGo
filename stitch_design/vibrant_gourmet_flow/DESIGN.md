---
name: Vibrant Gourmet Flow
colors:
  surface: '#f9f9ff'
  surface-dim: '#d4daeb'
  surface-bright: '#f9f9ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f1f3ff'
  surface-container: '#e8edff'
  surface-container-high: '#e3e8f9'
  surface-container-highest: '#dde2f4'
  on-surface: '#161c28'
  on-surface-variant: '#5b4137'
  inverse-surface: '#2b303e'
  inverse-on-surface: '#edf0ff'
  outline: '#8f7065'
  outline-variant: '#e4beb1'
  surface-tint: '#a73a00'
  primary: '#a73a00'
  on-primary: '#ffffff'
  primary-container: '#ff5c00'
  on-primary-container: '#521800'
  inverse-primary: '#ffb59a'
  secondary: '#565e72'
  on-secondary: '#ffffff'
  secondary-container: '#dae2fa'
  on-secondary-container: '#5b6478'
  tertiary: '#7c5800'
  on-tertiary: '#ffffff'
  tertiary-container: '#bf8900'
  on-tertiary-container: '#3b2800'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#ffdbce'
  primary-fixed-dim: '#ffb59a'
  on-primary-fixed: '#370e00'
  on-primary-fixed-variant: '#802a00'
  secondary-fixed: '#dae2fa'
  secondary-fixed-dim: '#bec6dd'
  on-secondary-fixed: '#121c2c'
  on-secondary-fixed-variant: '#3e475a'
  tertiary-fixed: '#ffdea8'
  tertiary-fixed-dim: '#ffba20'
  on-tertiary-fixed: '#271900'
  on-tertiary-fixed-variant: '#5e4200'
  background: '#f9f9ff'
  on-background: '#161c28'
  surface-variant: '#dde2f4'
typography:
  display-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 36px
    fontWeight: '700'
    lineHeight: 44px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 30px
    fontWeight: '700'
    lineHeight: 38px
    letterSpacing: -0.015em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  title-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: '600'
    lineHeight: 26px
  title-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 18px
  label-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.01em
  label-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.02em
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 14px
    letterSpacing: 0.02em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  margin: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system expresses culinary energy, swiftness, and modern mobile elegance. Tailored for on-the-go food discovery and rapid checkout, the visual direction merges high-contrast appetizing vibrancy with structured, premium Android mobile patterns. 

The emotional tone balances instant craving gratification with dependability. Visual weight leans toward generous whitespaces, rich organic food imagery, and hyper-legible touch affordances. Combining tactile, soft-edged geometries with modern fluid Material 3 paradigms, the aesthetic feels friendly, high-velocity, and premium without overwhelming visual clutter.

## Colors

The palette creates sharp visual distinction between functional chrome and mouthwatering focal points:

- **Primary (`#FF5C00`, gradient accent `#FF8038`)**: Radiates energy, appetite, and high-visibility urgency. Reserved strictly for primary action drivers, live delivery trackers, interactive states, and active navigation indicators.
- **Secondary (`#161F30`)**: Deep slate foundation delivering contrast and structural authority. Utilized for deep-tint badges, solid headers, floating counter elements, and contrasting secondary chips.
- **Tertiary (`#FFB800`)**: Amber gold dedicated to ratings, stellar culinary distinctions, reward points, and preparation milestones.
- **Neutral Dark (`#0D131F`)**: Deepest navy-slate anchor for primary typography, dominant icons, and critical structural lines.
- **Surface & Backgrounds**: Grounded in warm canvas tones (`#F8F9FB`) layered with pristine elevated sheet whites (`#FFFFFF`).
- **Subtle Boundaries & Feedback**: Ultra-soft borders (`#EEF2F6`) prevent visual harshness, while success states rely on vivid emerald green (`#10B981`) for order confirmations, fresh dietary markers, and driver locations.

## Typography

The typography scale utilizes **Plus Jakarta Sans** across all levels, capturing rounded modern terminals, high x-height, and open counters. This delivers the geometric warmth and crisp screen legibility required for dense nutritional menus, rapid scanning of prices, and bold dish marketing.

- **Headlines & Titles**: Emphasize weights `600` and `700` with subtle negative tracking on large scales (`-0.01em` to `-0.02em`) to bind multi-line dish names and banner promotions cleanly.
- **Body Text**: Tuned to weight `400` with generous line-height ratios (`1.4` to `1.5`) to preserve scannability on ingredients, allergen warnings, and merchant descriptions.
- **Labels & Badges**: Rely on weights `500` and `600` with slight positive tracking to ensure clear recognition on mini status pills, ETA counters, and micro-steppers.

## Layout & Spacing

Engineered specifically around the 393x852px native viewport:

- **Layout Structure**: Employs a 4-column mobile fluid layout using a `16px` (`1rem`) gutter and `16px` (`1rem`) outer canvas margin. Content containers stay within standard safe areas (leaving dynamic room for top punch-hole cameras/status bars and native Android bottom navigation bars).
- **Rhythm System**: Base 4px/8px modular rhythm governs all vertical stacks. Micro distances (gaps between rating stars and text) strictly use `4px` (`space-xs`). Standard horizontal content card insets utilize `16px` (`space-md`), and inter-section vertical separation steps up between `24px` (`space-lg`) and `32px` (`space-xl`).
- **Touch Bounds**: Interactive elements maintain a minimum physical touch box of `48x48px`, regardless of visual icon size.

## Elevation & Depth

Visual hierarchy uses multi-stage ambient diffusion and light layering rather than harsh, heavy drop-shadows:

- **Level 0 (Flat Canvas)**: Hex `#F8F9FB` canvas with zero elevation.
- **Level 1 (Subtle Cards & Lists)**: Pure white `#FFFFFF` surfaces wrapped with a 1px border of `#EEF2F6` and a soft ambient shadow (`0px 4px 16px rgba(13, 19, 31, 0.04)`).
- **Level 2 (Interactive Floating Elements & Food Cards)**: Dynamic product surfaces, search bars, and quantity steppers feature an expanded blur (`0px 8px 24px rgba(13, 19, 31, 0.08)`).
- **Level 3 (Sticky Nav & Bottom Sheets)**: Pinned checkout sheets, floating live cart bars, and bottom navigation ribbons sit atop with `0px -4px 28px rgba(13, 19, 31, 0.12)`, optionally utilizing a `backdrop-filter: blur(16px)` on semi-translucent backplanes.
- **CTA Glow**: High-priority primary orange actions project a localized tinted bloom (`0px 8px 20px rgba(255, 92, 0, 0.28)`), emphasizing checkout velocity.

## Shapes

The shape system leverages organic, friendly contours to mirror modern tactile hardware:

- **Base Radius (`0.5rem` / `8px`)**: Applied to micro elements, price tags, and small badges.
- **Large Radius (`1rem` / `16px`)**: Applied to text input fields, dish option selectors, and secondary dialog surfaces.
- **Extra Large Radius (`1.5rem` / `24px`)**: Defines all featured food cards, category tiles, bottom-sheet top corners, and modal wrappers.
- **Full Pill (`9999px`)**: Reserved for CTA buttons, counter buttons, filter chips, rating badges, search bars, and active tabs.

## Components

### Buttons
- **Primary CTA**: Full pill shape, height `54px`, filled with `#FF5C00` (or horizontal gradient to `#FF8038`), bold text in `#FFFFFF`, subtle primary elevation glow. Minimum width spans 100% inside bottom checkout bars.
- **Secondary Button**: Full pill, height `48px`, background `#161F30`, label in `#FFFFFF`.
- **Tertiary / Ghost Button**: Height `48px`, transparent background, text in `#FF5C00` or `#161F30`, pill shape with active ripple on touch.

### Chips & Filters
- **Filter Chips**: Height `40px`, pill shape. Unselected: background `#FFFFFF`, border `1px solid #EEF2F6`, text `#161F30`. Selected: background `#FF5C00`, border `1px solid #FF5C00`, text `#FFFFFF`.
- **Badge Pills**: Height `24px` to `28px`, padding `0 10px`, typography `label-sm`. Gold badge for ratings (`#FFB800` icon, `#161F30` text, background `rgba(255, 184, 0, 0.12)`). Green badge for dietary/speed indicators (`#10B981` text, background `rgba(16, 185, 129, 0.12)`).

### Cards
- **Food Product Cards**: Background `#FFFFFF`, border-radius `24px`, border `1px solid #EEF2F6`, Level 1 ambient shadow. Top section houses aspect-ratio 4:3 or 1:1 rounded imagery with top-right favorite heart button (40px white blur circle). Bottom contains `title-md` item title, preparation time, price in `headline-sm` bold orange, and a 36px circular `+` quick-add button.
- **Restaurant Hero Card**: Full width, border-radius `24px`, containing full bleed imagery overlaid with bottom gradient vignette, hosting floating rating and delivery time pills.

### Input Fields
- **Search Bar**: Height `52px`, pill shape, background `#FFFFFF`, border `1px solid #EEF2F6`, icon leading in `#161F30`, placeholder text in muted slate (`#64748B`), Level 1 shadow.
- **Form Inputs**: Height `52px`, border-radius `16px`, background `#FFFFFF`, border `1.5px solid #EEF2F6`, shifting to `1.5px solid #FF5C00` on active focus.

### Lists & Quantity Steppers
- **Cart List Items**: Horizontal arrangement with `80x80px` rounded image (`16px` radius), title, customization notes in `body-sm`, and right-aligned quantity stepper.
- **Quantity Steppers**: Pill shape container, height `36px`, containing `-` and `+` touch icons with center value in `title-md`, utilizing soft gray container (`#F8F9FB`) or deep slate (`#161F30`) inverted variant.

### Navigation Elements
- **Bottom Navigation Bar**: Height `68px` plus safe-inset padding, background `#FFFFFF` with Level 3 top ambient shadow, housing 4-5 tab destinations. Active items use `#FF5C00` with subtle upper indicator dot or filled vector glyph; inactive items sit in `#64748B`.
- **Floating Cart Banner**: Pinned `16px` above bottom bar, height `56px`, pill-shaped, background `#161F30`, text in `#FFFFFF`, containing item count circle, total sum, and rightward action arrow.